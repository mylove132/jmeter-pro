package com.lzh.jmeter.system.service.impl;
import com.lzh.jmeter.system.domain.SysOperLog;
import com.lzh.jmeter.system.mapper.SysOperLogMapper;
import com.lzh.jmeter.system.service.ISysOperLogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SysOperLogServiceImp implements ISysOperLogService {

    private final SysOperLogMapper sysOperLogMapper;

    public SysOperLogServiceImp(SysOperLogMapper sysOperLogMapper) {
        this.sysOperLogMapper = sysOperLogMapper;
    }

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     * @return 结果
     */
    @Override
    public int insertOperlog(SysOperLog operLog)
    {
        return sysOperLogMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog)
    {
        return sysOperLogMapper.selectOperLogList(operLog);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds)
    {
        return sysOperLogMapper.deleteOperLogByIds(operIds);
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId)
    {
        return sysOperLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog()
    {
        sysOperLogMapper.cleanOperLog();
    }
}
