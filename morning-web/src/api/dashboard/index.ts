import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { WeatherVo } from "./types";

/**
 * 获取天气
 *
 * @param queryParams
 */
export function queryWeather(queryParams?: any): AxiosPromise<WeatherVo> {
  return request({
    url: "/api/v1/dashboard/get/weather",
    method: "get",
    params: queryParams,
  });
}
