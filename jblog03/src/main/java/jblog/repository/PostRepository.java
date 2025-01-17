package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	private SqlSession sqlSession;

	public PostRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	

	public int deleteByCategoryId(Long categoryId) {
		return sqlSession.delete("post.deleteByCategoryId", categoryId);
	}


	public int insert(PostVo postVo) {
		return sqlSession.insert("post.insert", postVo);
	}


	public List<PostVo> findAllByCategoryId(Long categoryId) {
		return sqlSession.selectList("post.findAllByCategoryId", categoryId);
	}


	public Long findRecentPostIdByCategoryId(Long categoryId) {
		return sqlSession.selectOne("post.findRecentPostIdByCategoryId", categoryId);
	}


	public PostVo findByCategoryIdAndId(Long categoryId, Long postId) {
		return sqlSession.selectOne("post.findByCategoryIdAndId", Map.of("categoryId", categoryId, "postId", postId));
	}

}
