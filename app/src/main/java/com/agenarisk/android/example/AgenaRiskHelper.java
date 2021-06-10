package com.agenarisk.android.example;

import android.os.StrictMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import uk.co.agena.minerva.util.Config;
import uk.co.agena.minerva.util.Logger;

public class AgenaRiskHelper {
    public static void init(String licenseKey) throws IOException {

        // Setting paths explicitly for Android compatibility
        Config.init(new String[]{
                "--directoryHome",
                Config.getDirectoryTempSystem(),
                "--directoryWorking",
                Config.getDirectoryTempSystem(),
                "--directoryNativeLibs",
                Config.getDirectoryTempSystem(),
                "--directoryProduct",
                Config.getDirectoryTempSystem(),
                "-Puk.co.agena.minerva.LicenseKey=" + licenseKey,
                "-Puk.co.agena.minerva.debug=true", // Use this line if you want to turn debug on
                "-Pcom.agenarisk.logger.level=10"   // Use this line if you want to turn debug level up
        });

        // Use this line for debug output
        Logger.initDefaults();

        String pathProduct = Config.getDirectoryAgenaRiskProduct() + File.separator + "product.json";
        File fileProduct = new File(pathProduct);
        fileProduct.getParentFile().mkdirs();
        copyResource("/product.json", fileProduct);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public static File copyTemp(String resource, String suff) throws IOException {
        InputStream inputStream = MainActivity.class.getResourceAsStream(resource);
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        File tempFile = File.createTempFile("agenarisk-test-", suff);
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        out.write(buffer);
        out.close();
        Logger.logIfDebug("Copying `" + resource + "` to: " + tempFile.getAbsolutePath());
        return tempFile;
    }

    public static void copyResource(String resource, File toFile) throws IOException {
        Logger.logIfDebug("Copying `" + resource + "` to: " + toFile);
        InputStream inputStream = MainActivity.class.getResourceAsStream(resource);
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        toFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(toFile);
        out.write(buffer);
        out.close();
    }
}
