package net.exploringspringboot.journalApp.Repository;

import net.exploringspringboot.journalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepositoryIMPL {

    @Autowired
    private MongoTemplate mongoTemplate ;

    public List <User> getUserForSA(){
        Query query = new Query();

        Criteria criteria = new Criteria();

        query.addCriteria(criteria.orOperator(
                Criteria.where("email").regex("/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$/."),
                Criteria.where("sentimentalAnalysis").is(true))
        );
//        query.addCriteria(Criteria.where("name").is("cmk"));
//        query.addCriteria(Criteria.where("anyfiled").ne("value"));
//        query.addCriteria(Criteria.where("age").lte(20));

        return  mongoTemplate.find(query, User.class);
    }
}
