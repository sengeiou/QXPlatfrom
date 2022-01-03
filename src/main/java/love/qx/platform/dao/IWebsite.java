package love.qx.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import love.qx.platform.entity.Website;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface IWebsite extends BaseMapper<Website> {
    //根据网站id修改是否上线
    @Update("update website set is_on_line=#{isOnLine} where website_id=#{websiteId}")
    public int updateIsOnLineById(@Param("websiteId") Integer websiteId,@Param("isOnLine") Integer isOnLine);


    //根据类型id查询该类型下的所有网站信息
    @Select("select * from website where content_id in (select content_id from content_name where type_id=#{typeId})")
    public List<Website> findOnLineByTypeId(@Param("typeId") Integer typeId);
}
