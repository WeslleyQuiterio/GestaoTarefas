package br.com.gestaotarefas.util;

import javax.persistence.EntityManager;

import br.com.lumi.persistence.util.EntityManagerAbstractUtil;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Classe utilitária que abstrai o acesso aos objetos EntityManager's de cada
 * base de dados.
 *
 * @author waelson.nunes
 *
 */
public class EntityManagerUtil extends EntityManagerAbstractUtil {

    private static EntityManagerUtil instance;
    private static String NAME_PU;

    private EntityManagerUtil() {
        NAME_PU = "GestaoTarefasPU";
    }

    public static EntityManagerUtil getInstance() {
        if (instance == null) {
            instance = new EntityManagerUtil();
        }
        return instance;
    }

    public static EntityManagerUtil getNewInstance() {
        return new EntityManagerUtil();
    }

    public EntityManager getEntityManager() {

        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        EntityManager etm = (EntityManager) sessao.getAttribute("entityManager");

        if (etm == null || !etm.isOpen()) {
            etm = Util.JpaEntityManagerFactory().createEntityManager();
            sessao.setAttribute("entityManager", etm);
        }

        return etm;
        /*ThreadLocal<EntityManager> threadLocal = getThreadLocal(PU_GESTOR);
		EntityManager em = threadLocal.get();
		if (em == null || !em.isOpen()) {
			em = getEntityManagerFactory(PU_GESTOR).createEntityManager();
			threadLocal.set(em);
		}*/

        //return getEntityManager(PU_GESTOR);
    }

}
