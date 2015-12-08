package com.company.speedment.test.sql696688;

import javax.annotation.Generated;

import com.speedment.internal.core.runtime.SpeedmentApplicationLifecycle;

/**
 * A {@link
 * com.speedment.internal.core.runtime.SpeedmentApplicationLifecycle} class
 * for the {@link com.speedment.config.Project} named sql696688.
 * <p>
 * This Class or Interface has been automatically generated by Speedment. Any
 * changes made to this Class or Interface will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public class Sql696688Application extends SpeedmentApplicationLifecycle<Sql696688Application> {
    
    public Sql696688Application() {
        setSpeedmentApplicationMetadata(new Sql696688ApplicationMetadata());
    }
    
    @Override
    protected void onInit() {
        loadAndSetProject();
        put(new com.company.speedment.test.sql696688.db0.sql696688.borrowed.impl.BorrowedManagerImpl(speedment));
        put(new com.company.speedment.test.sql696688.db0.sql696688.employee.impl.EmployeeManagerImpl(speedment));
        put(new com.company.speedment.test.sql696688.db0.sql696688.user.impl.UserManagerImpl(speedment));
        super.onInit();
    }
}