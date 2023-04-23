package com.application.billingsystem.services;

import com.application.billingsystem.entity.PaymentEntity;
import com.application.billingsystem.exceptions.IncorrectArgumentException;
import com.application.billingsystem.exceptions.PaymentNotFoundException;
import com.application.billingsystem.repositories.PaymentRepository;
import com.application.billingsystem.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<PaymentEntity> getAll() {
        return repository.findAll();
    }

    public List<PaymentEntity> getAllByNumberPhone(String numberPhone) {

        ValidationUtils.checkNumberPhone(numberPhone);

        return repository.findAllByNumberPhone(numberPhone);
    }

    public PaymentEntity getById(long id) {

        ValidationUtils.checkId(id);

        return repository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }

    @Transactional
    public void create(PaymentEntity payment) {

        validate(payment);

        repository.save(payment);
    }

    @Transactional
    public void update(PaymentEntity payment) {

        validate(payment);

        if (!repository.existsById(payment.getId())) {
            throw new PaymentNotFoundException("Payment not found");
        }

        repository.save(payment);
    }

    @Transactional
    public void delete(long id) {

        ValidationUtils.checkId(id);

        if (!repository.existsById(id)) {
            throw new PaymentNotFoundException("Payment is not exists");
        }

        repository.deleteById(id);
    }

    private void validate(PaymentEntity payment) {
        if (payment.getNumberPhone() == null) {
            throw new IncorrectArgumentException("Номер телефона не указан");
        }
        ValidationUtils.checkPayment(payment.getMoney());
    }
}
