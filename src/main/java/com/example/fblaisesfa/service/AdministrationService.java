package com.example.fblaisesfa.service;

import java.util.List;

import com.example.fblaisesfa.model.ProductFamily;
import com.example.fblaisesfa.model.Role;
import com.example.fblaisesfa.model.Sector;
import com.example.fblaisesfa.model.User;

public interface AdministrationService {
	public User findUserByEmail(String email);

	public User findUserById(Long id);

	public User findUserByUserName(String userName);

	public List<User> findAllUsers();

	public Long saveUser(User user);

	public Long updateUser(User userWithNewValues);

	public void deleteUser(Long id);

	public Sector findSectorById(Long id);

	public List<Sector> findAllSectors();

	public Long saveSector(Sector sector);

	public Long saveRole(Role role);

	public Long updateSector(Sector sectorWithNewValues);

	public void deleteSector(Long id);

	public ProductFamily findProductFamilyById(Long id);

	public List<ProductFamily> findAllProductFamilies();

	public Long saveProductFamily(ProductFamily family);

	public Long updateProductFamily(ProductFamily familyWithNewValues);

	public void deleteProductFamily(Long id);

	public Role findRoleByName(String name);
}