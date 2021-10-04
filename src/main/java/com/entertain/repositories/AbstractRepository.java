package com.entertain.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.entertain.utils.JpaUtil;

public class AbstractRepository<T> {
	// khai bao managerFactory
	public static final EntityManager ENTITY_MANAGER = JpaUtil.getEntityManager();
	
	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		ENTITY_MANAGER.close();
		super.finalize();
	}
	//find by id
	public T findById(Class<T> clazz, Integer id) {
		return ENTITY_MANAGER.find(clazz, id);
	}
	
	// find all
		// SELECT e FROM Entity e WHERE isActive = 1;
		// SELECT * FROM Entity ;
	public List<T> findAll(Class<T> clazz, boolean existIsActive) {
		
		// lay ten entity de truyen vao Query
		String entityName = clazz.getSimpleName();
		
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT e FROM ").append(entityName).append(" e");
		if(existIsActive == true) { // neu isActive ton tai thi entity do la User
			hql.append(" WHERE isActive = 1");
		}
		//thuc hien query
		TypedQuery<T> query = ENTITY_MANAGER.createQuery(hql.toString(), clazz);
		return query.getResultList();
	}
	
	// phan trang
		/* 
		 5 elements
		 1 trang chua 2 elements  --> tong so trang la 3  .... [0] [1] [2] [3] [4]
		 	trang 1 : [0] [1]
		 	trang 2 : [2] [3]
		 	trang 3 : [4]
		 	
		 - vi index bat dau tu 0. nhung client truyen vao tu 1 nen ta phai ( - 1 ) de bang 0
		 -
		  	+ pageNumber: so trang dang dung., do client truyen vao
		  	+ pageSize: so elements trong 1 trang
		  	
		  	- lay elements o trang hien tai
		  		+ trang 1: pageNumber = 1 - 1 ,  pageSize = 2 --> 0 * 2 = 0
		  													  --> bat dau lay tu elements thu 0. va tong cong co 2 phan tu
		  		+ trang 2: pageNumber = 2 - 1 ,  pageSize = 2 --> 1 * 2 = 2
		  													  --> bat dau lay tu elements thu 2. va tong cong co 2 phan tu
		  		+ trang 3: pageNumber = 3 - 1 ,  pageSize = 2 --> 2 * 2 = 4
		  													  --> bat dau lay tu elements thu 4. va tong cong co 2 phan tu
		 
		 */
	public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
		String entityName = clazz.getSimpleName();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT e FROM ").append(entityName).append(" e ");
		if(existIsActive == true) {
			hql.append("WHERE isActive = 1");
		}
		TypedQuery<T> query = ENTITY_MANAGER.createQuery(hql.toString(), clazz);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	//find one
	// findOne(User.Class, hql, "user", "password");
	public T findOne(Class<T> clazz, String hql, Object...params) {
		TypedQuery<T> query = ENTITY_MANAGER.createQuery(hql, clazz);
		
		// dem co bao nhieu param
		for(int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		// tranh truong hop query null se bi loi. nen dung List
		List<T> result = query.getResultList();
		if(result.isEmpty()) {
			return null;
		} 
		// vi fine one nen get(0)
		return result.get(0);
	}
	//find many hql
	public List<T> findMany(Class<T> clazz, String hql, Object...params) {
		TypedQuery<T> query = ENTITY_MANAGER.createQuery(hql, clazz);
		
		for(int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);		
		}
		return query.getResultList();
	}
	//find many sql
	@SuppressWarnings("unchecked")
	public List<Object[]> findMantByNativeQuery(Class<T> clazz, String sql, Object...params) {
		Query query = ENTITY_MANAGER.createNativeQuery(sql, clazz);
		
		for(int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}
	//create
	public T create(T entity) {
		try {
			ENTITY_MANAGER.getTransaction().begin();
			ENTITY_MANAGER.persist(entity);
			ENTITY_MANAGER.getTransaction().commit();
			
			System.out.println("Created Success");
			return entity;
		} catch (Exception e) {
			ENTITY_MANAGER.getTransaction().rollback();
			System.out.println("Cannot created" + entity.getClass().getSimpleName());
			throw new RuntimeException(e);
		}
	}
	//update
	public T update(T entity) {
		try {
			ENTITY_MANAGER.getTransaction().begin();
			ENTITY_MANAGER.merge(entity);
			ENTITY_MANAGER.getTransaction().commit();
			
			System.out.println("Updated Success");
			return entity;
		} catch (Exception e) {
			ENTITY_MANAGER.getTransaction().rollback();
			System.out.println("Cannot update" + entity.getClass().getSimpleName());
			throw new RuntimeException(e);
		}
	}
	//delete
		public T delete(T entity) {
			try {
				ENTITY_MANAGER.getTransaction().begin();
				ENTITY_MANAGER.remove(entity);
				ENTITY_MANAGER.getTransaction().commit();
				
				System.out.println("Delete Success");
				return entity;
			} catch (Exception e) {
				ENTITY_MANAGER.getTransaction().rollback();
				System.out.println("Cannot delete" + entity.getClass().getSimpleName());
				throw new RuntimeException(e);
			}
		}
}
