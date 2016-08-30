package com.bootcamp.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bootcamp.app.persistance.HibernateUtil;

public class App {
	
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		
		User matias = new User("matias", "matias@gmail.com");
		session.save(matias);
		Tag sports = new Tag("sports");
		session.save(sports);
		
		transaction.commit();
		session.close();
		
		
		/*
		User matias = new User("matias", "matias@gmail.com");
		User pablo = new User("pablo", "pablo@gmail.com");
		User alejo = new User("alejo", "alejo@gmail.com");
		
		Set<String> matiasTags = new HashSet<>();
		matiasTags.add("Books");
		TagsManager.getInstance().addTags(matiasTags);
		
		Set<String> pablosTags = new HashSet<>();
		pablosTags.add("Smartphones");
		pablosTags.add("Cars");
		TagsManager.getInstance().addTags(pablosTags);
		
		Set<String> alejosTags = new HashSet<>();
		alejosTags.add("Books");
		alejosTags.add("Cars");
		alejosTags.add("Sports");
		TagsManager.getInstance().addTags(alejosTags);
		
		TagsManager.getInstance().getTags().forEach(tag -> System.out.print(tag + " "));
		System.out.println();
		
		Group javaGroup = new Group("javaGroup");
		javaGroup.suscribeUser(matias);
		javaGroup.suscribeUser(pablo);
		
		PostsManager.getInstance().createPost("matiasGroupPost", "groupText", null, matias, javaGroup);
		
		PostsManager.getInstance().createPost("matiasPost1", "matiasText1", null, matias);
		PostsManager.getInstance().createPost("matiasPost2", "matiasText2", null, matias);
		PostsManager.getInstance().createPost("matiasPost3", "matiasText3", null, matias);
		PostsManager.getInstance().createPost("pablosPost1", "pablosText1", null, pablo);
		PostsManager.getInstance().createPost("pablosPost2", "pablosText2", null, pablo);
		PostsManager.getInstance().createPost("pablosPost3", "pablosText3", null, pablo);
		PostsManager.getInstance().createPost("alejosPost1", "alejosText1", null, alejo);
		PostsManager.getInstance().createPost("alejosPost2", "alejosText2", null, alejo);
		PostsManager.getInstance().createPost("alejosPost3", "alejosText3", null, alejo);
		Post matiasPost4 = PostsManager.getInstance().createPost("matiasPost4", "matiasText4", null, matias);
		PostsManager.getInstance().createPost("alejosPost4", "alejosText4", null, alejo);
		PostsManager.getInstance().createPost("alejosPost5", "alejosText5", null, alejo);
		
		PostsManager.getInstance().deletePost(matiasPost4);
		PostsManager.getInstance().sortByOldest(0).forEach(post -> System.out.println(post.getTitle()));		
		PostsManager.getInstance().sortByNewest(0).forEach(post -> System.out.println(post.getTitle()));
		PostsManager.getInstance().sortAlphabeticallyByTitle(0).forEach(post -> System.out.println(post.getTitle()));
		*/
		
	}
}
