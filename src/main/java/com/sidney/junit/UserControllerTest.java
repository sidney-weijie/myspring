package com.sidney.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;

// 相关注解
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserController.class, FileHelper.class})
@PowerMockIgnore("javax.management.*")
public class UserControllerTest {

    // @Autowired 属性的注入方式: 联合使用 @Mock 和 @InjectMocks
    // 下面的方式，将会mock出来一个 user service对象，将将其注入到 UserController 的实例 uc 中去。
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController uc;

    /**
     * mock普通方法
     *
     * @throws Exception
     */
    @Test
    public void testAddUser() throws Exception {
        UserDto ud = new UserDto();
        PowerMockito.when(userService.addUser(ud)).thenReturn(1);
        // can not stub like this
        // PowerMockito.doReturn(1).when(userService.addUser(ud));
        boolean result = uc.addUser(ud);
        Assert.assertEquals(result, true);
    }

    /**
     * mock抛异常
     *
     * @throws Exception
     */
    @Test
    public void testDelUser() throws Exception {
        int toDelete = 1;
        // 如果 user service 中的 delUser() 方法抛出的是 checked exception，那么，thenThrow() 里需要抛出 Exception()或者其子类；
        // 如果delUser() 方法抛出的是 unchecked exception，那么，thenThrow() 里需要抛出 RuntimeException()或其子类
        PowerMockito.when(userService.delUser(toDelete)).thenThrow(new Exception("mock exception"));
        boolean result = uc.delUser(toDelete);
        Assert.assertEquals(result, false);
    }

    /**
     * mock静态方法
     */
    @Test
    public void mockFileHelper() {
        PowerMockito.mockStatic(FileHelper.class);
        PowerMockito.when(FileHelper.getName("lucy")).thenReturn("lily");
        Assert.assertEquals(FileHelper.getName("lucy"), "lily");
    }

    /**
     * mock 返回值为 void 的方法
     *
     * @throws Exception
     */
    @Test
    public void testSaveUser() throws Exception {
        UserDto userDto = new UserDto();

        // way one:
        PowerMockito.doNothing().when(userService, "saveUser", userDto);

        // way two:
        PowerMockito.doNothing().when(userService).saveUser(userDto);

        uc.saveUser(userDto);
    }

    /**
     * mock私有方法<br />
     * 方法一<br />
     * PS:该方法中，还介绍了 mock私有字段的值 的方法。
     *
     * @throws Exception
     */
    @Test
    public void testModUser() throws Exception {
        UserDto ud = new UserDto();
        int moded = 1;

        PowerMockito.when(userService.modUser(ud)).thenReturn(moded);

        UserController uc2 = PowerMockito.mock(UserController.class);

        // 给没有 setter 方法的 私有字段 赋值。
        Whitebox.setInternalState(uc2, "userService", userService);

        // 因为要测试的是 modUser() 方法，
        // 所以，当调用这个方法时，应该让它调用真实的方法，而非被mock掉的方法
        PowerMockito.when(uc2.modUser(ud)).thenCallRealMethod();

        // 在modUser()方法中会调用verifyMod()这个私有方法，所以，需要将mock掉
        PowerMockito.when(uc2, "verifyMod", moded).thenReturn(true);

        boolean result = uc2.modUser(ud);

        Assert.assertEquals(result, true);
    }

    /**
     * mock私有方法<br />
     * 方法二
     *
     * @throws Exception
     */
    @Test
    public void testModUser2() throws Exception {
        UserDto ud = new UserDto();
        int moded = 1;

        PowerMockito.when(userService.modUser(ud)).thenReturn(moded);

        // 对uc进行监视
        uc = PowerMockito.spy(uc);
        // 当uc的verifyMod被执行时，将被mock掉
        PowerMockito.when(uc, "verifyMod", moded).thenReturn(true);
        boolean result = uc.modUser(ud);

        Assert.assertEquals(result, true);
    }

    /**
     * 测试私有方法(注意： 是测试，不是mock)<br />
     * 方法一
     *
     * @throws Exception
     */
    @Test
    public void testVerifyMod() throws Exception {
        // 获取Method对象，
        Method method = PowerMockito.method(UserController.class, "verifyMod", int.class);
        // 调用Method的invoke方法来执行
        boolean result = (boolean) method.invoke(uc, 1);
        Assert.assertEquals(result, true);
    }

    /**
     * 测试私有方法(注意： 是测试，不是mock)<br />
     * 方法二
     *
     * @throws Exception
     */
    @Test
    public void testVerifyMod2() throws Exception {
        // 通过 Whitebox 来执行
        boolean result = Whitebox.invokeMethod(uc, "verifyMod", 1);
        Assert.assertEquals(result, true);
    }

    /**
     * mock新建对象
     *
     * @throws Exception
     */
    @Test
    public void testCountUser() throws Exception {
        UserDto ud = new UserDto();
        ud.setId(1);

        PowerMockito.whenNew(UserDto.class).withNoArguments().thenReturn(ud);

        int count = uc.countUser();

        Assert.assertEquals(count, 1);
    }

    /**
     * 参数的模糊匹配
     */
    @Test
    public void mockFileHelper2() {
        PowerMockito.mockStatic(FileHelper.class);
        PowerMockito.when(FileHelper.getName(Matchers.anyString())).thenReturn("lily");
        Assert.assertEquals(FileHelper.getName("lucy"), "lily");
        Assert.assertEquals(FileHelper.getName("hanmeimei"), "lily");
    }
}