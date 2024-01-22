package com.qf.chat.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.qf.chat.entity.FriendRequest;
import org.apache.ibatis.annotations.Select;

/**
 * 好友申请表(FriendRequest)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
public interface FriendRequestDao extends BaseMapper<FriendRequest> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<FriendRequest> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<FriendRequest> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<FriendRequest> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<FriendRequest> entities);
//    @Select("""
//    select fr.id,u_id,f_id,notes,request_msg,u.header,u.status,u.nickname
//    from friend_request fr join user u on u_id=u.id
//    where f_id=#{uid}
//    order by
//    fr.create_time desc;
//    """)
    List<FriendRequest> queryMyRequest(Integer uid);
}

