import {PageContainer} from '@ant-design/pro-components';
import React, {useEffect, useState} from 'react';
import {Card, Descriptions, message} from 'antd';
import {
  getInterfaceInfoVoByIdUsingGet,
} from "@/services/myapi-backend/interfaceInfoController";
import {useParams} from "@@/exports";

const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [data,setData] = useState<API.InterfacesInfo>();
  const params=useParams();

  const loadData = async () => {
    //开始加载数据，设置loading状态为true
    if(!params.id){
      message.error('参数不存在');
      return;
    }
    setLoading(true);
    try{
      const res=await getInterfaceInfoVoByIdUsingGet({
        id: Number(params.id),
      });
      setData(res.data);
    }catch(error:any){
      message.error("请求失败"+error.message);
    }
    setLoading(false)
  }

  useEffect(() => {
    //页面加载完成之后调用加载数据的函数
    loadData();
  }, []);

  return (
    <PageContainer title="Esther 在线接口开发平台">
      <Card>
        {
          data ? (
            <Descriptions title={data.name} column={2}>
              <Descriptions.Item label="接口状态">{data.status?'开启':'关闭'}</Descriptions.Item>
              <Descriptions.Item label="描述">{data.description}</Descriptions.Item>
              <Descriptions.Item label="请求地址">{data.url}</Descriptions.Item>
              <Descriptions.Item label="请求方法">{data.method}</Descriptions.Item>
              <Descriptions.Item label="请求头">{data.requestHeader}</Descriptions.Item>
              <Descriptions.Item label="响应头">{data.responseHeader}</Descriptions.Item>
              <Descriptions.Item label="创建时间">{data.createTime}</Descriptions.Item>
              <Descriptions.Item label="更新时间">{data.updateTime}</Descriptions.Item>
            </Descriptions>
          ):(
            <>接口不存在</>
          )}
      </Card>
    </PageContainer>
  );
};

export default Index;
