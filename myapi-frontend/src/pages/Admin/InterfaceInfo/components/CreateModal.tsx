import {
  ProColumns, ProTable,
} from '@ant-design/pro-components';
import { Modal } from 'antd';
import React from 'react';

export type Props = {
  columns: ProColumns<API.InterfacesInfo>[],
  onCancel: () => void;
  onSubmit: (values:API.InterfacesInfo) => Promise<void>;
  visible: boolean;
};

const CreateModal: React.FC<Props> = (props) => {
  // 使用解构赋值获取props中的属性
  const { visible, columns, onCancel, onSubmit } = props;
  return <Modal open={visible} footer={null} onCancel={() => onCancel?.()}>
    <ProTable type="form" columns={columns} onSubmit={async (value) => {
      onSubmit?.(value);
    }}/>
  </Modal>
};

export default CreateModal;
