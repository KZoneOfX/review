package Review.r_util;

import org.apache.tools.zip.ZipEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 4/10/2016
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class R_FileUtils {
    public static String getFilePrefix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex);
    }

    public static String getFileSufix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
    }

    public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
        File sFile = new File(inputFile);
        File tFile = new File(outputFile);
        FileInputStream fis = new FileInputStream(sFile);
        FileOutputStream fos = new FileOutputStream(tFile);
        int temp = 0;
        try {
            while ((temp = fis.read()) != -1) {
                fos.write(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public static boolean fileDowmLoad(String file_ssource_path, String file_name, HttpServletResponse response) {
        File file = new File(file_ssource_path);
        try {
            InputStream in = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            response.addHeader(
                    "Content-Disposition",
                    "attachment;filename="
                            + new String((file_name).getBytes("GBK"),
                            "ISO-8859-1"));
            response.addHeader("Content-Length", file.length() + "");
            response.setCharacterEncoding("GBK");
            response.setContentType("application/octet-stream");
            int data = 0;
            while ((data = in.read()) != -1) {
                os.write(data);
            }
            os.close();
            in.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
