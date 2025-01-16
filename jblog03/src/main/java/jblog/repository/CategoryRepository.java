package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	private SqlSession sqlSession;

	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void insert(CategoryVo vo) {
		sqlSession.insert("category.insert", vo);
	}
	
	public List<CategoryVo> findAllByBlogId(String blogId) {
		return sqlSession.selectList("category.findAllByBlogId", blogId);
	}
	
	public List<CategoryVo> findAllAndPostCntByBlogId(String blogId) {
		return sqlSession.selectList("category.findAllAndPostCntByBlogId", blogId);
	}

	public int delete(Long categoryId) {
		return sqlSession.delete("category.delete", categoryId);		
	}

	public Long findDefaultCategoryId(String blogId) {
		return sqlSession.selectOne("category.findDefaultCategoryId", blogId);
	}

	public CategoryVo findByIdAndBlogId(Long categoryId, String blogId) {
		return sqlSession.selectOne("category.findByIdAndBlogId", Map.of("categoryId", categoryId, "blogId", blogId));
	}

}
