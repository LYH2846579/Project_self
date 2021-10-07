package dao.Sdao;

import bean.S;

import java.sql.Connection;

/**
 * @author LYHstart
 * @create 2021-10-07 10:40
 *
 * 针对S表的一些操作规范
 */
public interface SDAO
{
    public void update(Connection connection, S s);

    public void insert(Connection connection,S s);

    //...
}
