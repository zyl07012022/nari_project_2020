import com.nari.zyl.kafka.producer.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaProducerApplicationTest.class)
public class KafkaProducerApplicationTest {

//    @Autowired
//    private KafkaProducer kafkaProducer;

    @Test
    public void kafkaProducer(){
        new KafkaProducer().send();
    }

    @Test
    public void contextLoads() {
    }
}
