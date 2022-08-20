package com.mBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mBoard.entity.Board;

@Mapper
public interface BoardMapper {
	
	/* 게시물 목록 보기 */
	@Select("select seqno, userid, mwriter, mtitle, mregdate, mcontent from tbl_mboard order by seqno desc")
	List<Board> selectList();
	
	/* 게시물 내용 보기 */
	@Select("select seqno, userid, mwriter, mtitle, mregdate, mcontent from tbl_mboard where seqno=${seqno}")
	Board viewList(@Param("seqno") int seqno);
	
	/* 게시물 수정 */
	@Update("update tbl_mboard set mwriter=\'${mwriter}\', mtitle=\'${mtitle}\', mcontent=\'${mcontent}\' where seqno=${seqno}")
	void updateList(@Param("mwriter") String mwriter,
			@Param("mtitle") String mtitle, @Param("mcontent") String mcontent, @Param("seqno") int seqno);
	
	/* 게시물 등록 */
	@Insert("insert into tbl_mboard (userid, mwriter, mtitle, mcontent) values (\'${userid}\', \'${mwriter}\', \'${mtitle}\', \'${mcontent}\')")
	void insertList(@Param("userid") String userid,@Param("mwriter") String mwriter,
			@Param("mtitle") String title,@Param("mcontent") String mcontent);
	
	/* 게시물 삭제 */
	@Delete("delete from tbl_mboard where seqno=${seqno}")
	void deleteList(@Param("seqno") int seqno);

}
