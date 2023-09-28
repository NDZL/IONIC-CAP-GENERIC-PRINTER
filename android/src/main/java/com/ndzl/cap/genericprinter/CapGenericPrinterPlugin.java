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

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.security.AccessControlContext;

@CapacitorPlugin(name = "CapGenericPrinter")
public class CapGenericPrinterPlugin extends Plugin {

    private CapGenericPrinter implementation = new CapGenericPrinter();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", "#"+implementation.echo(value)+"|");
        call.resolve(ret);
    }

    @PluginMethod
    public void printHTML(PluginCall call){
        String html = call.getString("html");
        printViaPrintManager( html );
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
          .setMediaSize(ISO_A5 )
          .build();

        String jobName ="IONIC Print";
        PrintJob printJob = printManager.print(jobName, printAdapter, pa);

      }
    });

  }

}
