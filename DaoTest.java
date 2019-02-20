package ma.ensa.dao;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ma.ensa.entities.Client;
import ma.ensa.entities.Commande;
import ma.ensa.entities.Panier;
import ma.ensa.entities.Produit;
import ma.ensa.service.ICommandeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/configContext.xml")
public class DaoTest {
	
	@Autowired
	private Idao dao;
		
	Produit produit1;

	@Before
	public void setUp() throws Exception {
		
		produit1 = new Produit("produit1");
		produit1.setPrix(3000);		
	}

	@After
	public void tearDown() throws Exception {
		produit1 = null;
	}

	@Test
	public void testAddProduit() {
		assertEquals(1, dao.addProduit(new Produit("produit1")));
		assertEquals("produit1", dao.getProduitById(1).getDescription());
		dao.deleteProduit(1);
		assertNull(dao.getProduitById(1));
	}

}
