package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Fund;
import net.javaguides.springboot.repository.FundRepository;

@Service
public class FundServiceImpl implements FundService {

	@Autowired
	private FundRepository fundRepository;

	@Override
	public List<Fund> getAllFunds() {
		return fundRepository.findAll();
	}

	@Override
	public void saveFund(Fund fund) {
		this.fundRepository.save(fund);
	}

	@Override
	public Fund getFundById(long id) {
		Optional<Fund> optional = fundRepository.findById(id);
		Fund fund = null;
		if (optional.isPresent()) {
			fund = optional.get();
		} else {
			throw new RuntimeException(" Fund not found for id :: " + id);
		}
		return fund;
	}

	@Override
	public void deleteFundById(long id) {
		this.fundRepository.deleteById(id);
	}

	@Override
	public Page<Fund> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.fundRepository.findAll(pageable);
	}
}
