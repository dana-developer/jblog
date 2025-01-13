package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	private SqlSession sqlSession;

	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(BlogVo vo) {
		return sqlSession.insert("blog.insert", vo);
	}
}
