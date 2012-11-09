/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.jms.ObjectMessage;
import javax.ejb.EJB;
import javax.jms.JMSException;

/**
 *
 * @author Jaroslav Nespesny, 359566
 * @mail.muni.cz
 */
@MessageDriven(mappedName = "jms/AsyncDestinationMessageBean", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class AsyncDestinationMessageBean implements MessageListener {
    
    @EJB
    DestinationService destService;
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                Destination dest =
                        (Destination)((ObjectMessage) message).getObject();
                destService.create(dest);
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Incompatible message");
        }
    }
    
   
}
