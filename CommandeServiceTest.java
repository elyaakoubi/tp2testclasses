package ma.ensa.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ma.ensa.dao.Idao;
import ma.ensa.entities.Client;
import ma.ensa.entities.Commande;
import ma.ensa.entities.Panier;
import ma.ensa.entities.Produit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/configContext.xml")
public class CommandeServiceTest {
	
	@Autowired
	private Idao dao;
	
	@Autowired
	private ICommandeService commandeService;
	
	Produit produit1;
	Produit produit2;

	@Before
	public void setUp() throws Exception {
		
		produit1 = new Produit("produit1");
		produit1.setPrix(3000);
		produit2 = new Produit("produit2");
		produit2.setPrix(4000);
		
	}

	@After
	public void tearDown() throws Exception {
		produit1 = null;
		produit2 = null;
	}

	@Test
	public void testgetTotalPrix() {
		dao.addProduit(produit1);
		dao.addProduit(produit2);
		Client cl1 = new Client("nom1", "tel1");
		Panier panier = new Panier();
		panier.addItem(produit1, 3);
		panier.addItem(produit1, 6);
		panier.addItem(produit2, 5);
		commandeService.saveCommande(panier, cl1);
		Commande commande = dao.getCommandeById(1);
		commandeService.setCommande(commande);
		assertEquals(47000.0,commandeService.getTotalPrix(),0);
	}
	
	@Test
	public void testgetListLigneCommande() {
		dao.addProduit(produit1);
		dao.addProduit(produit2);
		Client cl1 = new Client("nom1", "tel1");
		Panier panier = new Panier();
		panier.addItem(produit1, 3);
		panier.addItem(produit1, 6);
		panier.addItem(produit2, 5);
		commandeService.saveCommande(panier, cl1);
		Commande commande = dao.getCommandeById(1);
		commandeService.setCommande(commande);
		assertEquals(2,dao.getList(commande.getIdCommande()).size());
	}
	
}
