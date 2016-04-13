package Review.r_server.userInfo;


import Review.r_util.MD5Util;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/28/2016
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoMapper userInfoMapper;


    public int insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    public int deleteUserInfo(UserInfo userInfo) {
        return userInfoMapper.deleteUserInfo(userInfo);
    }

    public UserInfo selectUserInfoByUserId(Long user_id) {
        return userInfoMapper.selectUserInfoByUserId(user_id);
    }

    public int countUserInfos(Map<String, Object> map) {
        return userInfoMapper.countUserInfos(map);
    }

    public int countStudents(Map<String, Object> map) {
        return userInfoMapper.countStudents(map);
    }

    public List<UserInfo> selectStudentList() {
        return userInfoMapper.selectStudentList();
    }

    public List<UserInfo> selectUserInfos(UserInfo userInfo) {
        return userInfoMapper.selectUserInfos(userInfo);
    }

    public List<UserInfo> selectUserInfosByPage(Map<String, Object> map) {
        return userInfoMapper.selectUserInfosByPage(map);
    }

    /**
     * 处理上传excel 中的数据
     *
     * @param file_path
     * @return
     */
    public Map<String, Object> importStudentFromExcel(String file_path) {
        Map<String, Object> msg = new HashMap<>();
        MD5Util md5Util = new MD5Util();
        InputStream inputStream;
        XSSFWorkbook xssfWorkbook;
        try {
            inputStream = new FileInputStream(file_path);
            xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet;
            XSSFRow xssfRow;
            UserInfo userInfo;
            List<UserInfo> userInfoList = new ArrayList<>();
            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
                xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                for (int rowNum = 1; rowNum < xssfSheet.getLastRowNum(); rowNum++) {
                    xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null && !(GVFSC(xssfRow.getCell(0)).trim().equals(""))) {
                        userInfo = new UserInfo();
                        userInfo.setUsername(GVFSC(xssfRow.getCell(0)).trim());
                        userInfo.setPassword(md5Util.compute(userInfo.getUsername() + GVFSC(xssfRow.getCell(1)).trim()));
                        userInfo.setReal_name(GVFSC(xssfRow.getCell(2)).trim());
                        userInfo.setPlace(GVFSC(xssfRow.getCell(3)).trim());
                        userInfo.setStu_tch_name(GVFSC(xssfRow.getCell(4)).trim());
                        userInfoList.add(userInfo);
                    }
                }
            }
            System.out.println("@@@@@@@@@@@@@@" + userInfoList.size());
            msg.put("userInfoList", userInfoList);
            msg.put("result", "success");
        } catch (IOException e) {
            msg.put("result", "failure");
            e.printStackTrace();
        }

        return msg;
    }

    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    /**
     * getValueFromXSSFCell
     *
     * @param xssfCell
     * @return
     */
    public String GVFSC(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf((long) (xssfCell.getNumericCellValue()));
        } else {
            // 返回字符串类型的值
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }

}
