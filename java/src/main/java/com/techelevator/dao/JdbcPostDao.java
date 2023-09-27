package com.techelevator.dao;
import com.techelevator.model.Post;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
@Component

public class JdbcPostDao implements PostDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addPost(String username, String caption, String imageUrl) {
        String sql = "INSERT INTO posts (username, caption, image_url) VALUES (?, ?, ?) RETURNING post_id";
        try {
            jdbcTemplate.queryForObject(sql, int.class, username, caption, imageUrl);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Post> getFeed(String username) {
        List<Post> myPost = new ArrayList<>();
        return myPost;
    }

    @Override
    public Post findPostById(int postId) {
        Post myPost2 = new Post();
        return myPost2;
    }

    private Post mapRowToRow(SqlRowSet row) {
        Post post = new Post();
        post.setId(row.getInt("post_id"));
        post.setUserId(row.getInt("user_id"));
        post.setCaption(row.getString("caption"));
        post.setImgURL(row.getString("image_url"));
        post.setTimestamp(row.getTimestamp("timestamp"));
        return post;
    }
}
