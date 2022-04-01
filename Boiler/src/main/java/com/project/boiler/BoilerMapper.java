package com.project.boiler;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BoilerMapper {	
	
	@Select("select id, temp, on_off from tbl_test order by id")
	public List<Member> selectBoilerstat();

	@Update("update tbl_test set on_off = \'${on_off}\' where id=\'${id}\'")
	public void updateon_off(@Param("on_off") String on_off, @Param("id") String id);

}
