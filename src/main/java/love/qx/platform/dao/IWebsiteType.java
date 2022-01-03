package love.qx.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import love.qx.platform.entity.WebsiteType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface IWebsiteType extends BaseMapper<WebsiteType> {
    //根据类型id修改是否上线
    @Update("update website_type set is_on_line=#{isOnLine} where type_id=#{typeId}")
    public int updateIsOnLineByTypeId(@Param("isOnLine") Integer isOnLine,@Param("typeId") Integer typeId);

    //根据类型id修改类型名称
    @Update("update website_type set type_name=#{typeName} where type_id=#{typeId}")
    public int updateTypeNameByTypeId(@Param("typeName") String typeName,@Param("typeId") Integer typeId);



}
