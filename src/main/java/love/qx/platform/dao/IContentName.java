package love.qx.platform.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import love.qx.platform.entity.ContentName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface IContentName extends BaseMapper<ContentName> {
    //根据总名称id修改是否上线
    @Update("update content_name set is_on_line=#{isOnLine} where content_id=#{contentId}")
    public int updateIsOnLineByContentId(@Param("isOnLine") Integer isOnLine,@Param("contentId") Integer contentId);
}
