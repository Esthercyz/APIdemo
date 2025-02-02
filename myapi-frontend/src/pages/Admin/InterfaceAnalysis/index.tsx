import '@umijs/max';
import React, {useEffect, useState} from 'react';
import ReactECharts from 'echarts-for-react';
import {PageContainer} from "@ant-design/pro-components";
import {listTopInvokeInterfaceInfoUsingPost} from "@/services/myapi-backend/interfaceAnalysisController";
/**
 * 接口分析
 * @constructor
 */
const InterfaceAnalysis: React.FC = () => {

  const [data,setData] = useState<API.InterfacesInfoVO[]>([]);
  const [loading,setLoading] = useState(true);

  useEffect(() => {
    // 定义 async 函数来发送请求
    const fetchData = async () => {
      try {
        const res = await listTopInvokeInterfaceInfoUsingPost({
          limit: 1
        });  // 将参数以对象形式传递
        if (res.data) {
          setData(res.data);  // 设置响应数据
        }
      } catch (e: any) {
        console.error("Error fetching data", e);  // 错误处理
      }
    };
    fetchData();  // 调用 async 函数
  },[])

  //映射:{value:1048,name:'Search Engine'}
  const chartData = data.map(item => {
    return {
      value: item.totalNum,
      name: item.name
    }
  })

  const option = {
    legend: {
      top: 'top'
    },
    toolbox: {
      show: true,
      feature: {
        mark: { show: true },
        dataView: { show: true, readOnly: false },
        restore: { show: true },
        saveAsImage: { show: true }
      }
    },
    series: [
      {
        name: '接口调用次数统计',
        type: 'pie',
        radius: [80, 120],
        center: ['50%', '50%'],
        roseType: 'area',
        itemStyle: {
          borderRadius: 6
        },
        data: chartData
      }
    ]
  };

  return (
    <PageContainer>
      <ReactECharts
        style={{width: '100%',height:'500px'}}
        loadingOption={{ showLoading: loading }}
        option={option}
      />
    </PageContainer>
  );
};

export default InterfaceAnalysis;
