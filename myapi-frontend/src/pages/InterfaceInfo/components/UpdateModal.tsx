import {
  ProColumns, ProTable,
} from '@ant-design/pro-components';
import { Modal } from 'antd';
import React, {useEffect, useRef} from 'react';
import {ProFormInstance} from "@ant-design/pro-form/lib";

export type Props = {
  values: API.InterfacesInfo;
  columns: ProColumns<API.InterfacesInfo>[],
  onCancel: () => void;
  onSubmit: (values:API.InterfacesInfo) => Promise<void>;
  visible: boolean;
};

const UpdateModal: React.FC<Props> = (props) => {
  // 使用解构赋值获取props中的属性
  const { values,visible, columns, onCancel, onSubmit } = props;
  const formRef = useRef<ProFormInstance>();
  useEffect(()=>{
    if(formRef){
      formRef.current?.setFieldsValue(values);
    }
  },[values])
  return <Modal open={visible} footer={null} onCancel={() => onCancel?.()}>
    <ProTable
      type="form"
      columns={columns}
      form={{
        initialValues:values
      }}
      onSubmit={async (value) => {
      onSubmit?.(value);
    }}/>
  </Modal>
};

export default UpdateModal;
