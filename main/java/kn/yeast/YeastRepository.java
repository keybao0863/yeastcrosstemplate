package kn.yeast;

import org.springframework.data.repository.CrudRepository;

public interface YeastRepository extends CrudRepository<Master, String> {
	public Master findByName(String name);

}
