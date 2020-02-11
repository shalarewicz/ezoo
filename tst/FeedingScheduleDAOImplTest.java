import java.util.Set;

import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.dao.FeedingScheduleDAOImpl;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImplTest {

  public static void main(String[] args){
    FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
    
    Set<FeedingSchedule> schedules = dao.getAllSchedules();

    for (FeedingSchedule fs : schedules) {
      System.out.println(fs);
    }
  }
}
