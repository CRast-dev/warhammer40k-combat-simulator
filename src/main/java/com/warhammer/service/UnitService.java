
package com.warhammer.service;

import com.warhammer.dto.CreateModelRequestDTO;
import com.warhammer.dto.CreateUnitRequestDTO;
import com.warhammer.dto.CreateWeaponRequestDTO;
import com.warhammer.repository.ModelRepository;
import com.warhammer.repository.UnitRepository;
import com.warhammer.repository.WeaponRepository;
import java.sql.SQLException;

@Service
public class UnitService {
    private final UnitRepository unitRepository;
    private final ModelRepository modelRepository;
    private final WeaponRepository weaponRepository;

    public UnitService(UnitRepository unitRepository, ModelRepository modelRepository, WeaponRepository weaponRepository){
        this.unitRepository = unitRepository;
        this.modelRepository = modelRepository;
        this.weaponRepository = weaponRepository;
    }
    @Transactional
    public void createUnit(CreateUnitRequestDTO request) {
        //TODO
        int unitId = unitRepository.createUnit(request.name());
        for (CreateModelRequestDTO modelRequest : request.models()) {
            int modelId = modelRepository.createModel(modelRequest);
            unitRepository.addModel(unitId, modelId, modelRequest.quantity());
            for (CreateWeaponRequestDTO weaponRequest : modelRequest.weapons()) {
                int weaponId = weaponRepository.createWeapon(weaponRequest);
                modelRepository.addWeapon(modelId, weaponId, weaponRequest.quantity());
            }
        }
    }
}
