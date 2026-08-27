
package com.warhammer.service;

import com.warhammer.dto.CreateModelRequestDTO;
import com.warhammer.dto.CreateUnitRequestDTO;
import com.warhammer.dto.CreateWeaponRequestDTO;
import com.warhammer.repository.ModelRepository;
import com.warhammer.repository.UnitRepository;
import com.warhammer.repository.WeaponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

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
    public void deleteUnit(int unitId) throws SQLException {
        List<Integer> modelIds = unitRepository.getModelIdsForUnit(unitId);
        for (int modelId : modelIds) {
            List<Integer> weaponIds = modelRepository.getWeaponIdsForModel(modelId);
            boolean modelIsShared = unitRepository.isModelUsedByAnotherUnit(modelId, unitId);
            if (!modelIsShared) {
                for (int weaponId : weaponIds) {
                    boolean weaponIsShared = modelRepository.isWeaponUsedByAnotherModel(weaponId, modelId);
                    modelRepository.deleteModelWeaponLinks(modelId);
                    if (!weaponIsShared) {
                        weaponRepository.deleteWeapon(weaponId);
                    }
                }
                modelRepository.deleteModel(modelId);
            }
        }
        unitRepository.deleteUnitModelLinks(unitId);
        unitRepository.deleteUnit(unitId);
    }




    @Transactional
    public void createUnit(CreateUnitRequestDTO request) throws  SQLException {
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
