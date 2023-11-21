package ejb;

import com.parking.parkinglot.entities.Car;
import common.CarDto;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars(){
        LOG.info("findAllCars");
        try{
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();

            return copyCarsToDto(cars);
        }catch (Exception e){
            throw new EJBException(e);
        }
    }


    public List<CarDto> copyCarsToDto(List<Car> cars)
    {
        List<CarDto> carDtos = new ArrayList<>();
        for (Car c : cars)
        {
            carDtos.add(new CarDto(c.getId(), c.getLicensePlate(), c.getParkingSpot(), c.getOwner().getUsername()));
        }

        return carDtos;
    }
}