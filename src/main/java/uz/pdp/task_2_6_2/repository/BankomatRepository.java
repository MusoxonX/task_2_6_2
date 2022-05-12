package uz.pdp.task_2_6_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_6_2.entity.Bankomat;

public interface BankomatRepository extends JpaRepository<Bankomat,Integer> {
}
