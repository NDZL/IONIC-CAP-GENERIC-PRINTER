package com.ndzl.cap.genericprinter;

import static android.print.PrintAttributes.MediaSize.ISO_A5;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.webkit.WebView;

import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.security.AccessControlContext;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream ;
import java.io.OutputStream;
import android.content.Context;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
/*
  HOW TO BUILD THE IONIC-CAPACITOR APP AFTER THE ANDROID SIDE HAS CHANGED
  https://stackoverflow.com/questions/76248825/after-using-the-command-ionic-cap-add-android-i-changed-my-code-in-vscode-but-no
    ionic build
    npx cap sync
    npx cap copy
    npx cap open android

  FROM THE IONIC-GENERATED DOC
    npm install generic-printer
    npx cap sync
  */

@CapacitorPlugin(name = "CapGenericPrinter")
public class CapGenericPrinterPlugin extends Plugin {

  public static byte[] readAsset(Context context, String filename)
    throws IOException {
    InputStream in = context.getResources().getAssets().open(filename);
    try {
      return readAllBytes(in);
    } finally {
      in.close();
    }
  }

  static byte[] readAllBytes(InputStream in) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    copyAllBytes(in, out);
    return out.toByteArray();
  }

  /**
   * Copies all available data from in to out without closing any stream.
   *
   * @return number of bytes copied
   */
  public static int copyAllBytes(InputStream in, OutputStream out)
    throws IOException {
    int byteCount = 0;
    byte[] buffer = new byte[4096];
    while (true) {
      int read = in.read(buffer);
      if (read == -1) {
        break;
      }
      out.write(buffer, 0, read);
      byteCount += read;
    }
    return byteCount;
  }

  private CapGenericPrinter implementation = new CapGenericPrinter();

  @PluginMethod
  public void echo(PluginCall call) {
    String value = call.getString("value");

    JSObject ret = new JSObject();
    ret.put("value", "#" + implementation.echo(value) + "|");
    call.resolve(ret);
  }

  @PluginMethod
  public void printHTML(PluginCall call) {
    String html = call.getString("html");
    printViaPrintManager(html);

    call.resolve();
  }

  @PluginMethod
  public void addDWProfileSSM(PluginCall call) {
    String TAG = "CapGenericPrinterPlugin";
    Log.i(TAG, "PluginMethod addDWProfileSSM");

    copyAssetToDownload();
    addDWProfileViaSSM();

    call.resolve();
  }

  @PluginMethod
  public void addDWProfileENTERPRISE(PluginCall call) {
    String TAG = "CapGenericPrinterPlugin";
    Log.i(TAG, "PluginMethod addDWProfileENTERPRISE");

    copyAssetToEnterprise();

    call.resolve();
  }

  void printViaPrintManager(String what) {

    getContext().getMainExecutor().execute(new Runnable() { //webView needs to be instanced on the UI thread
      @Override
      public void run() {
        WebView webView = new WebView(getContext());

        PrintManager printManager = (PrintManager) getContext().getSystemService(Context.PRINT_SERVICE);

        String htmlDocument = "<html><body><h1>Printing from Zebra Android!</h1><p>Testing, " +
          "testing, testing...</p></body></html>";
        webView.loadDataWithBaseURL(null, what, "text/HTML", "UTF-8", null);


        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter("NDZL");  //funziona!

        Bundle bundle = new Bundle();
        bundle.putBoolean(PrintDocumentAdapter.EXTRA_PRINT_PREVIEW, false);

        PrintAttributes pa = new PrintAttributes.Builder()
          .setColorMode(PrintAttributes.COLOR_MODE_MONOCHROME)
          // .setDuplexMode(PrintAttributes.DUPLEX_MODE_NONE)
          .setMediaSize(ISO_A5)
          .build();

        String jobName = "IONIC Print";
        PrintJob printJob = printManager.print(jobName, printAdapter, pa);

      }
    });

  }

  String DW_PROFILE_ENTERPRISE = "dwprofile_ionic-via-enterprise-autoimport.db";
  void copyAssetToEnterprise() {
    try {
      copyAllBytes(
        new ByteArrayInputStream(readAsset(getContext(), DW_PROFILE_ENTERPRISE)),
        new FileOutputStream(new java.io.File("/enterprise/device/settings/datawedge/autoimport/"+DW_PROFILE_ENTERPRISE))
      );

      Process _p = Runtime.getRuntime().exec("chmod 666 /enterprise/device/settings/datawedge/autoimport/"+DW_PROFILE_ENTERPRISE); //chmod needed for /enterprise
      _p.waitFor();
    } catch (IOException e) {
      Log.e("excp file copy", e.getMessage());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  String DW_PROFILE_SSM = "dwprofile_ionic-via-ssm.db";
  void copyAssetToDownload() {
    try {
      copyAllBytes(
        new ByteArrayInputStream(readAsset(getContext(), DW_PROFILE_SSM)),
        new FileOutputStream(new java.io.File("/storage/emulated/0/Download/"+DW_PROFILE_SSM))
      );

      Process _p = Runtime.getRuntime().exec("chmod 666 /storage/emulated/0/Download/"+DW_PROFILE_SSM);
      _p.waitFor();
    } catch (IOException e) {
      Log.e("excp file copy", e.getMessage());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private final String AUTHORITY_FILE = "content://com.zebra.securestoragemanager.securecontentprovider/files/";
  private final String RETRIEVE_AUTHORITY = "content://com.zebra.securestoragemanager.securecontentprovider/file/*";
  private final String COLUMN_DATA_NAME = "data_name";
  private final String COLUMN_DATA_VALUE = "data_value";
  private final String COLUMN_DATA_TYPE = "data_type";
  private final String COLUMN_DATA_PERSIST_REQUIRED = "data_persist_required";
  private final String COLUMN_TARGET_APP_PACKAGE = "target_app_package";

  private final String _target_package = "com.symbol.datawedge";
  private final String _target_sig = "";
  private final String TAG = "IONICAPPLUGIN";

  void addDWProfileViaSSM() {
    Uri cpUriQuery = Uri.parse(AUTHORITY_FILE + getContext().getPackageName());
    Log.i(TAG, "authority  " + cpUriQuery.toString());

    String sourcePath = "/storage/emulated/0/Download/"+DW_PROFILE_SSM;
    String targetPath = "com.symbol.datawedge/config/"+DW_PROFILE_SSM;

    try {
      ContentValues values = new ContentValues();
      String _package_sig = "{\"pkg\":\"" + _target_package + "\",\"sig\":\"" + _target_sig + "\"}";
      String allPackagesSigs = "{\"pkgs_sigs\":[" + _package_sig + "]}";

      values.put(COLUMN_TARGET_APP_PACKAGE, allPackagesSigs);
      values.put(COLUMN_DATA_NAME, sourcePath);
      values.put(COLUMN_DATA_TYPE, "3");
      values.put(COLUMN_DATA_VALUE, targetPath);
      values.put(COLUMN_DATA_PERSIST_REQUIRED, "false");
      Uri createdRow = getContext().getContentResolver().insert(cpUriQuery, values);
      Log.i(TAG, "SSM Insert File: " + createdRow.toString());

    } catch (Exception e) {
    }

  }
}
