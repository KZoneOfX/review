package Review.r_server.paperInfo;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/30/2016
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PaperInfoMapper {
    int insertPaperInfo(PaperInfo paperInfo);

    int updatePaperInfo(PaperInfo paperInfo);

    int deletePaperInfo(PaperInfo paperInfo);

    PaperInfo selectPaperInfoByUserId(Long user_id);

    PaperInfo selectPaperInfoByPaperId(String id);

    List<PaperInfo> selectPaperInfoList(PaperInfo paperInfo);


}
