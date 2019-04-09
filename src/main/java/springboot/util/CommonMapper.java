package springboot.util;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 该接口不能被扫描到，不能和mapper文件放一起
 * */
@RegisterMapper
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
