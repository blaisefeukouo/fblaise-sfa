package com.example.fblaisesfa.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fblaisesfa.AttributesNames;
import com.example.fblaisesfa.model.ProductFamily;
import com.example.fblaisesfa.model.Role;
import com.example.fblaisesfa.model.Sector;
import com.example.fblaisesfa.model.User;
import com.example.fblaisesfa.repository.ProductFamilyRepository;
import com.example.fblaisesfa.repository.RoleRepository;
import com.example.fblaisesfa.repository.SectorRepository;
import com.example.fblaisesfa.repository.UserRepository;

@Service("administrationService")
public class AdministrationServiceImpl implements AdministrationService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SectorRepository sectorRepository;
	@Autowired
	private ProductFamilyRepository productFamilyRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Long saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role userRole = roleRepository.findByRole(AttributesNames.Role_Admin);
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		return user.getId();
	}

	public Role findRoleByName(String name) {
		return roleRepository.findByRole(name);
	}

	public Long saveRole(Role role) {
		roleRepository.save(role);
		return role.getId();
	}

	@Override
	public Long updateUser(User userWithNewValues) {
		User user = userRepository.findById(userWithNewValues.getId());
		user.copyValuesFrom(userWithNewValues, bCryptPasswordEncoder);
		userRepository.save(user);
		return user.getId();
	}

	@Override
	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	public Sector findSectorById(Long id) {
		return sectorRepository.findById(id);
	}

	@Override
	public List<Sector> findAllSectors() {
		return sectorRepository.findAll();
	}

	@Override
	public Long saveSector(Sector sector) {
		sectorRepository.save(sector);
		return sector.getId();
	}

	@Override
	public Long updateSector(Sector sectorWithNewValues) {
		Sector sector = sectorRepository.findById(sectorWithNewValues.getId());
		sector.copyValuesFrom(sectorWithNewValues);
		sectorRepository.save(sector);
		return sector.getId();
	}

	@Override
	public void deleteSector(Long id) {
		// if(Sector.isActuallyUsed(id))
		sectorRepository.delete(id);
	}

	@Override
	public ProductFamily findProductFamilyById(Long id) {
		return productFamilyRepository.findById(id);
	}

	@Override
	public List<ProductFamily> findAllProductFamilies() {
		return productFamilyRepository.findAll();
	}

	@Override
	public Long saveProductFamily(ProductFamily productFamily) {
		productFamilyRepository.save(productFamily);
		return productFamily.getId();
	}

	@Override
	public Long updateProductFamily(ProductFamily familyWithNewValues) {
		ProductFamily productFamily = productFamilyRepository.findById(familyWithNewValues.getId());
		productFamily.copyValuesFrom(familyWithNewValues);
		productFamilyRepository.save(productFamily);
		return productFamily.getId();
	}

	@Override
	public void deleteProductFamily(Long id) {
		// if(ProductFamily.isActuallyUsed(id))
		productFamilyRepository.delete(id);
	}
}
