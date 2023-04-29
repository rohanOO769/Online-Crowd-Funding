package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Fund;

public interface FundService {
	List<Fund> getAllFunds();
	void saveFund(Fund fund);
	Fund getFundById(long id);
	void deleteFundById(long id);
	Page<Fund> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
