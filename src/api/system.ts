import { defHttp } from '/@/utils/http/axios';
import { TrafficParams } from '/@/api/model/systemModel';

enum Api {
  getTraffic = '/traffic',
}

export const getTraffic = (params: TrafficParams) => defHttp.get({ url: Api.getTraffic, params });
