/**
 *
 */
package struts2maven.Struts2MavenTest002.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 *
 * 完全パッケージ名称で取得
 * Spring 3.0 BeanNameGenerator ( for Struts2 Action )
 * @author A-pZ ( http://www.h3.dion.ne.jp/~alpha-pz/ )
 *
 */
public class Struts2ActionBeanNameGenerator implements BeanNameGenerator {

	private static final Logger log = Logger.getLogger(Struts2ActionBeanNameGenerator.class);

	/**
	 * @see org.springframework.beans.factory.support.BeanNameGenerator#generateBeanName(org.springframework.beans.factory.config.BeanDefinition, org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	public String generateBeanName(BeanDefinition definition,
			BeanDefinitionRegistry registry) {

		String className = definition.getBeanClassName();

		log.debug("* generateBeanName : " + className);

		return className;
	}

}
