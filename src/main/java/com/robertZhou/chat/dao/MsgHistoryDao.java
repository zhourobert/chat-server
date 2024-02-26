package com.robertZhou.chat.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.robertZhou.chat.entity.vo.FriendAndLastMsgVo;
import com.robertZhou.chat.entity.MsgHistory;
import org.apache.ibatis.annotations.Param;

/**
 * 历史消息表(MsgHistory)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-12 12:12:07
 */
public interface MsgHistoryDao extends BaseMapper<MsgHistory> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<MsgHistory> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<MsgHistory> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<MsgHistory> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<MsgHistory> entities);

    List<FriendAndLastMsgVo> loadFriendsAndLastMsgs(Integer id);
}

