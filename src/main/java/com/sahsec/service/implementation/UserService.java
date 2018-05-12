package com.sahsec.service.implementation;

import java.awt.List;
import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahsec.DTO.UserDto;
import com.sahsec.entities.Category;
import com.sahsec.entities.Payment;
import com.sahsec.entities.Payment.PaymentType;
import com.sahsec.entities.User;
import com.sahsec.exception.EmailExistsException;
import com.sahsec.exception.UserAlreadyExistException;
import com.sahsec.persistence.dao.RoleRepository;
import com.sahsec.persistence.dao.UserRepository;
import com.sahsec.persistence.dao.implementation.RoleRepositoryImpl;
import com.sahsec.persistence.dao.implementation.UserRepositoryImpl;
import com.sahsec.service.UserServiceInterface;

@Service("userService")
@Transactional
public class UserService implements UserServiceInterface {
	
	@Autowired
    private UserRepository repository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    
    public UserService() {
    	passwordEncoder = new BCryptPasswordEncoder();
    	roleRepository = new RoleRepositoryImpl();
    	repository = new UserRepositoryImpl();
    }

	
	public static User findBySso(String ssoId) {
		
		Session db = DatabaseService.getSession();
		CriteriaBuilder builder = db.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.where(builder.equal(root.get("m_name"), ssoId));
		Query<User> query = db.createQuery(criteria);
		
		java.util.List<User> results = query.getResultList();
		if(!results.isEmpty() && results.size() == 1) {
			return query.getSingleResult();
		}
		
		return null;
	}
	
	public static User findByEmail(String email) {
		
		Session db = DatabaseService.getSession();
		CriteriaBuilder builder = db.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.where(builder.equal(root.get("m_email"), email));
		Query<User> query = db.createQuery(criteria);
		
		java.util.List<User> results = query.getResultList();
		if(!results.isEmpty() && results.size() == 1) {
			return query.getSingleResult();
		}
		
		return null;
	}
    
    private boolean emailExist(String email) {
        User user = findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
    
    @Override
    public User registerNewUserAccount(final UserDto accountDto) throws UserAlreadyExistException {
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setName(accountDto.getName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return repository.save(user);
    }

	@Override
	public User getUser(String verificationToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRegisteredUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	 

}
