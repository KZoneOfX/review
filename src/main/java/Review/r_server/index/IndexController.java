package Review.r_server.index;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Xiaoke Zhang
 * Date: 2/28/2016
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class IndexController {

    private static Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
