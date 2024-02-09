import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.ndzl.ionic-printer',
  appName: 'native-printer',
  webDir: 'www',
  server: {
    androidScheme: 'https'
  }
};

export default config;
