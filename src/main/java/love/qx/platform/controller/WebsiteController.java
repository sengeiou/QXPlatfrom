package love.qx.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import love.qx.platform.entity.ContentName;
import love.qx.platform.entity.UserUrl;
import love.qx.platform.entity.Website;
import love.qx.platform.service.WebsiteService;
import love.qx.platform.vo.Result;
import love.qx.platform.vo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
网站详细信息表
 */


@RestController
@RequestMapping("/website")
public class WebsiteController {
    @Autowired
    WebsiteService websiteService;
    //根据网站id更新网站信息
    @PostMapping("/updateById")
    public Result updateById(@RequestBody Website website){
        if (website.getWebsiteId()==0||website.getContentId()==0||
            website.getAddTime()==null||website.getIntroduce()==null||
            website.getIsOnLine()==0||website.getWebsiteName()==null||
            website.getWebsiteUrl()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = websiteService.updateById(website);
        if (b)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }
    //根据网站id修改是否上线
    @PostMapping("/updateIsOnLineById")
    public Result updateIsOnLineById(@RequestBody Website website){
        if (website.getIsOnLine()==0||website.getWebsiteId()==0)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        int i = websiteService.updateIsOnLineById(website.getWebsiteId(), website.getIsOnLine());
        if (i!=0)return Result.success(ResultCode.UPDATEDATASUCCESS);
        return Result.failed(ResultCode.UPDATEDATAFAILURE);
    }
    //查询所有网站信息
    @GetMapping("/findAll")
    public Result findAll(){
        List<Website> list = websiteService.list();
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //查询所有已上线的网站信息
    @GetMapping("/findAllIsOnLine")
    public Result findAllIsOnLine(@RequestParam("typeId") Integer typeId){
        List<Website> isOnLine = websiteService.findOnLineByTypeId(typeId);
        return Result.success(ResultCode.GETDATASUCCESS,isOnLine);
    }
    //根据网站id查询一条网站信息
    @GetMapping("/findById")
    public Result findById(@RequestParam("websiteId") Integer websiteId){
        if (websiteId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        Website byId = websiteService.getById(websiteId);
        if (byId!=null)return Result.success(ResultCode.GETDATASUCCESS,byId);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据总名称id查询一组网站信息
    @GetMapping("/findByContentId")
    public Result<List<Website>> findByContentId(@RequestParam("contentId") Integer contentId){
        if (contentId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Website> wrapper=new QueryWrapper<>();
        List<Website> content_id = websiteService.list(wrapper.eq("content_id", contentId));
        if (content_id!=null)return Result.success(ResultCode.GETDATASUCCESS,content_id);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据总名称id查询一组已上线的网站信息
    @GetMapping("/findByContentIdAndOnLine")
    public Result<List<Website>> findByContentIdAndOnLine(@RequestParam("contentId") Integer contentId){
        if (contentId==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Website> wrapper=new QueryWrapper<>();
        List<Website> content_id = websiteService.list(wrapper.eq("content_id", contentId).and(w->w.eq("is_on_line",2)));
        if (content_id!=null)return Result.success(ResultCode.GETDATASUCCESS,content_id);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }

    //根据网站名称/网站详细介绍/网站链接模糊查询一组网站信息
    @GetMapping("/findByNameOrUrlOrIntroduce")
    public Result findByNameOrUrlOrIntroduce(@RequestParam("info") String info){
        if (info==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Website> wrapper=new QueryWrapper<>();
        List<Website> list = websiteService.list(wrapper.like("website_name", info)
                .or(w -> w.like("website_url", info))
                .or(w -> w.like("introduce", info)));
        if (list!=null)return Result.success(ResultCode.GETDATASUCCESS,list);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据日期查询一组数据
    @GetMapping("/findByDate")
    public Result<List<Website>> findByDate(@RequestParam("date") String date){
        if (date==""||date==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Website> wrapper=new QueryWrapper<>();
        List<Website> add_time = websiteService.list(wrapper.like("add_time", date));
        return Result.success(ResultCode.GETDATASUCCESS,add_time);
    }

    //查询上线/下线网站信息
    @GetMapping("/findByIsOnLine")
    public Result findByIsOnLine(@RequestParam("isOnLine") Integer isOnLine){
        if (isOnLine==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        QueryWrapper<Website> wrapper=new QueryWrapper<>();
        List<Website> is_on_line = websiteService.list(wrapper.like("is_on_line", isOnLine));
        if (is_on_line!=null)return Result.success(ResultCode.GETDATASUCCESS,is_on_line);
        return Result.failed(ResultCode.GETDATAFAILURE);
    }
    //根据网站id删除一条网站信息
    @PostMapping("/delByWebsiteId")
    public Result delByWebsiteId(@RequestBody Website website){
        if (website.getWebsiteId()==0)return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = websiteService.removeById(website.getWebsiteId());
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //根据总名称id删除一组网站信息
    @PostMapping("/delByWebsiteIds")
    public Result delByWebsiteIds(@RequestBody List<Integer> websites){
        if (websites==null)return Result.failed(ResultCode.PARAMETERMISTAKE);
        boolean b = websiteService.removeByIds(websites);
        if (b)return Result.success(ResultCode.DELETEDATASUCCESS);
        return Result.failed(ResultCode.DELETEDATAFAILURE);
    }
    //添加一条网站详细信息
    @PostMapping("/addWebsite")
    public Result addWebsite(@RequestBody Website website){
        if (website.getWebsiteId()!=0||website.getContentId()==0||
                website.getAddTime()==null||website.getIntroduce()==null||
                website.getWebsiteName()==null||
                website.getWebsiteUrl()==null||website.getWebsiteImage()==null)
            return Result.failed(ResultCode.PARAMETERMISTAKE);
        website.setIsOnLine(2);
        boolean save = websiteService.save(website);
        if (save)return Result.success(ResultCode.ADDDATASUCCESS);
        return Result.failed(ResultCode.ADDDATAFAILURE);
    }

}
