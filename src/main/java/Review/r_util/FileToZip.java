package Review.r_util;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 4/10/2016
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */

import org.apache.tools.zip.ZipEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

public final class FileToZip {
    private FileToZip() {
    }


    /**
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     *
     * @param sourceFilePath :待压缩的文件路径
     * @param zipFilePath    :压缩后存放路路径
     * @param fileName       :压缩后文件的名称
     * @return
     */
    public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName, String insideDir) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if (sourceFile.exists() == false) {
            System.out.println("待压缩的文件目路径" + sourceFilePath + "不存在.");
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                if (zipFile.exists()) {
                    zipFile.delete();
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
                    System.out.println("已覆盖源文件");
                }
                File[] sourceFiles = sourceFile.listFiles();
                //					System.out.println(sourceFiles.length);
                if (null == sourceFiles || sourceFiles.length < 1) {
                    System.out.println("待压缩的文件目录径" + sourceFilePath + "里面不存在文件，无需压缩.");
                } else {
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(fos);
                    try {
                        zip(zos, sourceFile, insideDir);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    flag = true;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                //关闭文件流
                try {
                    //							if(null != bis) bis.close();
                    if (null != zos) zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("success");
        return flag;
    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            System.out.println(base);
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            //			System.out.println(base);
            FileInputStream in = new FileInputStream(f);
            int b;
            System.out.println(">>>>>>压缩的文件中包含:" + base + " .<<<<<<");
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

}
