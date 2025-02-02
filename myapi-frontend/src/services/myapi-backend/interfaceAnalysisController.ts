// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** listTopInvokeInterfaceInfo POST /api/interfacesInfo/analysis/top/interface/invoke */
export async function listTopInvokeInterfaceInfoUsingPost(
  body: API.InterfaceAnalysisQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListInterfacesAnalysisVO_>(
    '/api/interfacesInfo/analysis/top/interface/invoke',
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      data: body,
      ...(options || {}),
    },
  );
}
