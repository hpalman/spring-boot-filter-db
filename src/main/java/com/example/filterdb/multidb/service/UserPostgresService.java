package com.example.filterdb.multidb.service;

import com.example.filterdb.multidb.model.UserPostgres;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPostgresService {

    private final SqlSession sqlSession;

    public UserPostgresService(@Qualifier("postgresSqlSessionTemplate") SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public UserPostgres findById(Long id) {
        return sqlSession.selectOne("UserMapperPostgres.findById", id);
    }

    public List<UserPostgres> findAll() {
        return sqlSession.selectList("UserMapperPostgres.findAll");
    }

    public void save(UserPostgres user) {
        if (user.getId() == null) {
            sqlSession.insert("UserMapperPostgres.insert", user);
        } else {
            sqlSession.update("UserMapperPostgres.update", user);
        }
    }

    public void delete(Long id) {
        sqlSession.delete("UserMapperPostgres.delete", id);
    }
}
