package service;

import entity.MatBang;
import repository.MatBangRepository;
import java.util.Date;
import java.util.List;

public class MatBangService implements IMatBangService {
    private MatBangRepository matBangRepository = new MatBangRepository();

    @Override
    public List<MatBang> getAll() {
        return matBangRepository.getAll();
    }

    @Override
    public List<MatBang> search(String loaiMatBang, Integer tang, Date ngayBD, Date ngayKT) {
        return matBangRepository.search(loaiMatBang, tang, ngayBD, ngayKT);
    }

    @Override
    public boolean add(MatBang matBang) {
        // Kiểm tra mã mặt bằng đã tồn tại chưa
        if (isMatBangExists(matBang.getMaMatBang())) {
            return false;
        }
        return matBangRepository.add(matBang);
    }

    @Override
    public void remove(String maMatBang) {
        matBangRepository.remove(maMatBang);
    }

    @Override
    public boolean isMatBangExists(String maMB) {
        return matBangRepository.isMatBangExists(maMB);
    }
}

