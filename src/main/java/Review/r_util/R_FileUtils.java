package Review.r_util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
}
