package Review.r_server.paperInfo;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 3/30/2016
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaperInfoServiceImpl implements PaperInfoService {
    private PaperInfoMapper paperInfoMapper;

    public PaperInfoMapper getPaperInfoMapper() {
        return paperInfoMapper;
    }

    public void setPaperInfoMapper(PaperInfoMapper paperInfoMapper) {
        this.paperInfoMapper = paperInfoMapper;
    }

    public int insertPaperInfo(PaperInfo paperInfo) {
        return paperInfoMapper.insertPaperInfo(paperInfo);
    }

    public int updatePaperInfo(PaperInfo paperInfo) {
        return paperInfoMapper.updatePaperInfo(paperInfo);
    }

    public int deletePaperInfo(PaperInfo paperInfo) {
        return paperInfoMapper.deletePaperInfo(paperInfo);
    }

    public PaperInfo selectPaperInfoByUserId(Long user_id) {
        return paperInfoMapper.selectPaperInfoByUserId(user_id);
    }

    public PaperInfo selectPaperInfoByPaperId(String id) {
        return paperInfoMapper.selectPaperInfoByPaperId(id);
    }

    public List<PaperInfo> selectPaperInfoList(PaperInfo paperInfo) {
        return paperInfoMapper.selectPaperInfoList(paperInfo);
    }
}
